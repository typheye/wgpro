/*
 * MD5 (RFC 1321) - UTF-8 aware.
 * Returns the lowercase 32-character hex digest.
 * Used for Typheye V2 API request signatures (login2 / legacy token).
 */
function toUtf8Bytes(str) {
	const bytes = [];
	for (let i = 0; i < str.length; i++) {
		let c = str.charCodeAt(i);
		if (c < 0x80) {
			bytes.push(c);
		} else if (c < 0x800) {
			bytes.push(0xc0 | (c >> 6), 0x80 | (c & 0x3f));
		} else if (c >= 0xd800 && c <= 0xdbff && i + 1 < str.length) {
			const c2 = str.charCodeAt(++i);
			const cp = 0x10000 + ((c & 0x3ff) << 10) + (c2 & 0x3ff);
			bytes.push(
				0xf0 | (cp >> 18),
				0x80 | ((cp >> 12) & 0x3f),
				0x80 | ((cp >> 6) & 0x3f),
				0x80 | (cp & 0x3f)
			);
		} else {
			bytes.push(0xe0 | (c >> 12), 0x80 | ((c >> 6) & 0x3f), 0x80 | (c & 0x3f));
		}
	}
	return bytes;
}

const S = [
	7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22,
	5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
	4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23,
	6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21,
];

const K = [
	0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
	0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501,
	0x698098d8, 0x8b44f7af, 0xffff5bb1, 0x895cd7be,
	0x6b901122, 0xfd987193, 0xa679438e, 0x49b40821,
	0xf61e2562, 0xc040b340, 0x265e5a51, 0xe9b6c7aa,
	0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
	0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed,
	0xa9e3e905, 0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a,
	0xfffa3942, 0x8771f681, 0x6d9d6122, 0xfde5380c,
	0xa4beea44, 0x4bdecfa9, 0xf6bb4b60, 0xbebfbc70,
	0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
	0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665,
	0xf4292244, 0x432aff97, 0xab9423a7, 0xfc93a039,
	0x655b59c3, 0x8f0ccc92, 0xffeff47d, 0x85845dd1,
	0x6fa87e4f, 0xfe2ce6e0, 0xa3014314, 0x4e0811a1,
	0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391,
];

function add32(a, b) {
	return (a + b) & 0xffffffff;
}

function rol(n, c) {
	return (n << c) | (n >>> (32 - c));
}

function md5(input) {
	const bytes = toUtf8Bytes(String(input == null ? "" : input));
	const bitLen = bytes.length * 8;

	// Padding: 0x80, then zeros until length ≡ 56 (mod 64), then 64-bit little-endian bit length.
	const withPadding = bytes.slice();
	withPadding.push(0x80);
	while (withPadding.length % 64 !== 56) withPadding.push(0);

	const lenLo = bitLen & 0xffffffff;
	const lenHi = Math.floor(bitLen / 4294967296) & 0xffffffff;
	for (let i = 0; i < 4; i++) withPadding.push((lenLo >>> (8 * i)) & 0xff);
	for (let i = 0; i < 4; i++) withPadding.push((lenHi >>> (8 * i)) & 0xff);

	let a0 = 0x67452301;
	let b0 = 0xefcdab89;
	let c0 = 0x98badcfe;
	let d0 = 0x10325476;

	const M = new Array(16);
	for (let offset = 0; offset < withPadding.length; offset += 64) {
		for (let i = 0; i < 16; i++) {
			const j = offset + i * 4;
			M[i] =
				(withPadding[j] |
					(withPadding[j + 1] << 8) |
					(withPadding[j + 2] << 16) |
					(withPadding[j + 3] << 24)) >>>
				0;
		}

		let A = a0;
		let B = b0;
		let C = c0;
		let D = d0;
		for (let i = 0; i < 64; i++) {
			let F;
			let g;
			if (i < 16) {
				F = (B & C) | (~B & D);
				g = i;
			} else if (i < 32) {
				F = (D & B) | (~D & C);
				g = (5 * i + 1) % 16;
			} else if (i < 48) {
				F = B ^ C ^ D;
				g = (3 * i + 5) % 16;
			} else {
				F = C ^ (B | ~D);
				g = (7 * i) % 16;
			}
			F = add32(F, add32(A, add32(K[i], M[g])));
			A = D;
			D = C;
			C = B;
			B = add32(B, rol(F, S[i]));
		}
		a0 = add32(a0, A);
		b0 = add32(b0, B);
		c0 = add32(c0, C);
		d0 = add32(d0, D);
	}

	return [a0, b0, c0, d0]
		.map((word) => {
			let out = "";
			for (let i = 0; i < 4; i++) {
				out += ("0" + ((word >>> (8 * i)) & 0xff).toString(16)).slice(-2);
			}
			return out;
		})
		.join("");
}

export default md5;
export { md5 };
