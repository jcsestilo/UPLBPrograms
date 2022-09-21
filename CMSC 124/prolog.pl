goodWeapon(beidou).
goodWeapon('Yae Miko').
artifactSet(jun, 'Yae Miko').
artifactSet(jun, ganyu).
ascends(jun, ningguang).
ascends(jun, ganyu).
artifactSet(hoshi, ningguang).
artifactSet(hoshi, 'Yae Miko').
ascends(hoshi, ganyu).
ascends(hoshi, beidou).
artifactSet(wonwoo, ganyu).
artifactSet(wonwoo, beidou).
ascends(wonwoo, 'Yae Miko').
ascends(wonwoo, ningguang).
artifactSet(woozi, beidou).
artifactSet(woozi, ningguang).
ascends(woozi, ganyu).
ascends(woozi, ningguang).
strongerChar(PLAYER ,CHARACTER) :- artifactSet(PLAYER, CHARACTER);
						goodWeapon(CHARACTER), ascends(PLAYER, CHARACTER).