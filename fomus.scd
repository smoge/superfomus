
// Example:

a = Fomus.new([
	(voice: 1, dur:0.2342, note:2),
	(voice: 2, dur:1.23498756, pitch:3),
	(voice: 1, dur:3.87634521876, pitch:1)
])

f.eventList
f.eventList
f.write
f.make
f.show 	// instead of write/make/show
		// you can do "f.plot"

/*
 * You can set your favorite PDF viewer
 * and custom file name:
 */

a.pdfViewer = "okular"
a.fileName = "~/Desktop/fomusTest"

/*
 * You can include Events, Array of Events or Streams:
 *
 * The plot method will write the file, call fomus/lilypond and
 * also the PDF viewer application.
 *
 * A MIDI file can be generated from the
 * lilypond file produced by FOMUS
 * 
 */

// Adapted from James Harkins' "A Practical Guide to Patterns":
s.boot // make sure the audio server is running

p = Pbind(
	\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
	\dur, Prand([0.125, 0.25, 0.5], inf));

p.play


f = Fomus();

/* You must specify how many
 * events should be created by the Stream
 * put(Stream, number-of-elements)
 */

f.put(p.asStream, 40)
f.eventList
f.write
f.make
f.show

/* If you don't want quatertones
 * you can change the FOMUS header:
 */

f.header = "quatertones = no \n"
f.plot;

p = Pbind(
	\note, Prand((-12,-11.5..24), inf),
	\dur, Prand([0.125, 0.25, 0.3], inf)
);

p.play;

f = Fomus()
f.put(p.asStream, 30)
f.plot
