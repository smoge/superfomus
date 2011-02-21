/*
	You can include Events, Array of Events or Streams:

	The plot method will write the file, call fomus/lilypond and
	also the PDF viewer application.

	A MIDI file can be generated from the
	lilypond file produced by FOMUS

	You can set your favorite PDF viewer
	and custom file name as a Fomus header:
	If lily-view-exe-path is not correctly set
	the resulting PDF won't show up. Just to make sure
	edit the path:

	You must specify how many
	events should be created by the Stream
	put(Stream, number-of-elements)

*/

f = Fomus()

f.header = "
quartertones = yes \n
lily-exe-path = /usr/bin/lilypond \n
lily-view-exe-path = /usr/bin/okular \n";

/*

	You can also choose any filename
	and path to put your generated files:

*/

f.fileName = "~/Desktop/fomusTest"


// Adapted from James Harkins' "A Practical Guide to Patterns":

s.boot

p = Pbind(
	\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
	\dur, Prand([0.125, 0.25, 0.5], inf)
);

//p.play

f.put(p.asStream, 40) // put the next 40 Events in our Fomus object

f.makeLy // make and show a Lilypond Score
f.makeMidi // make a MIDI file
f.makeXml // make a MusicXML file

//////////////////////
// Another example: //
//////////////////////

p = Pbind(
	\note, Prand((-12,-11.5..24), inf),
	\dur, Prand([0.125, 0.25, 0.3], inf)
);

p.play;

f = Fomus(p.asStream, 30)
f.makeLy
f.makeMidi
f.makeXml

