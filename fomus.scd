/*
You can include Events, Array of Events or Streams:

You can set your favorite PDF viewer
and custom file name as a Fomus header:
If lily-view-exe-path is not correctly set
the resulting PDF won't show up. Just to make sure
edit the path:

You also must specify how many
events should be created by the Stream
put(Stream, number-of-elements)

*/

f = Fomus()

// configuration
// these are the defaults
// change if needed:

f.fileName = "~/Desktop/SuperFomus" // working path and filename
f.lilyPath = "/usr/bin/lilypond" // where is lilypond?
f.lilyViewPath = "/usr/bin/okular" // software to show the pdf
f.qt = true // quatertones

// Pattern adapted from James Harkins' "A Practical Guide to Patterns":
s.boot
p = Pbind(
	\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
	\dur, Prand([0.125, 0.25, 0.5], inf)
);

//p.play

f.put(p.asStream, 40) // put the next 40 Events in our Fomus object
f.write
f.makeLy // make and show a Lilypond Score

f.qt = false // if you want to round to halftones
f.makeLy

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

