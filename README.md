SuperFomus
==========

SuperFomus is a project that aims to integrate the features of the language SuperCollider (incluing support for Events, Routines and Streams) with the automatic music notation system FOMUS (FOrmat MUSic).

Website
-------

    http://www.github.com/smoge/superfomus

Dependencies
------------

You need LilyPond and Fomus installed on your system!


Useful Links
------------

- [SuperCollider](http://supercollider.sourceforge.net/)
- [LilyPond](http://lilypond.org/)
- [Fomus](http://sourceforge.net/projects/fomus/)


Usage
=====

You can include Events, Array of Events and Streams. This means that you can generate you data as Events, like this:


		[ 	( 'note': -1.1663100719452, 'dur': 0.25 ),
				( 'note': -0.25006246566772, 'dur': 0.5 ),
				( 'note': -4.7850661277771, 'dur': 0.125 )]


You also create a Patterns andspecify how many events should be created by the Stream

		put(Stream, number-of-elements)

----------

First of all, create your Fomus object, with or without initial content:

		f = Fomus()

	
These are the defaults options, change if needed;

Working path and filename:

		f.fileName = "~/Desktop/SuperFomus" 

Where is lilypond?
		f.lilyPath = "/usr/bin/lilypond" // 

Application to show the pdf file:
		f.lilyViewPath = "/usr/bin/okular"

Do we want to round to quatertone of halftones (boolean):
		
		f.qt = true


Let's create a Pattern:

		// Pattern adapted from James Harkins' "A Practical Guide to Patterns":
		s.boot
		p = Pbind(
			\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
							\dur, Prand([0.125, 0.25, 0.5], inf)
		);

		//p.play

Put the next 40 Events in our Fomus object:
		
		f.put(p.asStream, 40)

Make and show a Lilypond Score

		f.ly
		
If you want to round to halftones

		f.qt = false
		f.ly
		
Make a MIDI file

		f.midi

Make a MusicXML file

		f.xml

Another example:

		p = Pbind(
			\note, Prand((-12,-11.5..24), inf),
							\dur, Prand([0.125, 0.25, 0.3], inf)
		);

		p.play;

		f = Fomus(p.asStream, 30)
		f.ly
		f.midi
		f.xml

