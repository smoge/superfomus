
// Test Code


a = Fomus.new([
	(voice: 1, off: 0, dur:0.2342, pitch:2),
	(voice: 1, off: 0, dur:1.23498756, pitch:3),
	(voice: 1, off: 0, dur:3.87634521876, pitch:1)
])

a.put((voice: 1, off: 0, dur:3.87634521876, pitch:1))

a.plot

s.boot
p = Pbind( \note, Prand((-12..12), inf),
		   \dur, Prand([0.1, 0.2, 0.3, 0.4, 0.5, 1, 1.5, 2], inf))

p.play;
p.asStream.nextN(25,())

a = Fomus();
a.put(p.asStream, 30)
a.fNoteList
a.plot