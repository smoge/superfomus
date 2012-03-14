0
s.boot

p = Pbind(
	\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
	\dur, Prand([0.1, 0.1, 0.1, 0.1, 0.2, 0.3], inf)
);

p.play

f = Fomus()
f.put(p.asStream, 40)
f.ly
f.midi
f.xml

p = Pbind(
	\note, Prand((-12,-11.5..24), inf),
	\dur, Prand([0.125, 0.25, 0.3], inf)
);

p.play;

f = Fomus(p.asStream, 30)
f.ly
f.midi
f.xml

