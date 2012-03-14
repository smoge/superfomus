# SuperFomus


SuperFomus is a project that aims to integrate the features of the
language SuperCollider (incluing support for Events, Routines and
Streams) with the automatic music notation system FOMUS (FOrmat
MUSic).

See fomus.scd for some examples. 


## Website

    https://github.com/smoge/superfomus


------------------------------------------------------------

This project is hosted here:

    git clone git://github.com/smoge/superfomus.git SuperFomus


## Dependencies

You will need LilyPond and FoMus installed on your system.


## Links

- [SuperCollider](http://supercollider.sourceforge.net/)
- [LilyPond](http://lilypond.org/)
- [Fomus](http://sourceforge.net/projects/fomus/)


## Usage

You can include Events, Array of Events and Streams. This means that
you can generate your data as Events:


		[( 'note': 1, 'dur': 0.25  )
		,( 'note': -1, 'dur': 0.5  )
		,( 'note': 0, 'dur': 0.125 )
		]


Or... you can create a Pattern, turn it into a Stream, and specify how
many events should be created by this Stream

		put(Stream, number-of-elements)


## Examples

First of all, create your Fomus object, with or without initial
content:

		f = Fomus()

	
These are the default options. Change if needed.

Working path and filename (optional):

		f.fileName = "~/Desktop/SuperFomus" 

Where is lilypond (optional):

		f.lilyPath = "/usr/bin/lilypond"

Application to show the pdf file (optional)

		f.lilyViewPath = "/usr/bin/okular"

Do we want to round to quatertone of halftones as Boolean (optional):
		
		f.qt = true


Let's create a Pattern:

		// Pattern adapted from James Harkins' "A Practical Guide to Patterns":
		s.boot
		p = Pbind(
			\note, Pif(Pwhite(0.0, 1.0, inf) < 0.7, Pwhite(-7.0, 0, inf), Pwhite(7.0, 14, inf)),
			\dur, Prand([0.125, 0.25, 0.5], inf)
		);

		p.play

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
        
        
