
a = Fomus.new([
	(voice: 1, off: 0, dur:0.2342, pitch:2),
	(voice: 1, off: 0, dur:1.23498756, pitch:3),
	(voice: 1, off: 0, dur:3.87634521876, pitch:1)
])

a.eventList

/*
 * You can set your favorite PDF viewer
 * and set the file name:
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

a.put((voice: 1, off: 0, dur:3.87634521876, pitch:1))
a.plot

s.boot

p = Pbind(
	\note, Prand((-12..12), inf),
	\dur, Prand([0.1, 0.2, 0.3, 0.4, 0.5, 1, 1.5, 2], inf));

p.play;

f = Fomus();

/* You must specify how many
 * events should be created by the Stream
 * put(Stream, number-of-elements)
 */

f.put(p.asStream, 30)
f.fNoteList
f.plot
