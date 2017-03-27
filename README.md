# Fomus

SuperCollider bindings to Fomus Music Notation (FOrmat MUSic).

``FOMUS is a open source software that automates many musical notation tasks for composers and musicians [...] Once the composer loads or inputs their materials, FOMUS outputs a file suitable for importing into a graphical notation program such as LilyPond, MuseScore, Finale, Sibelius and others.''

See Help file for more information.

## Project website:

    https://github.com/smoge/superfomus

## Fomus website:

    http://fomus.sourceforge.net/

## Examples

```supercollider
(
a = 12.collect({|i|
  (
    'midinote': (
      (63 + rrand(-6,6.5) + [0,8,13]) ++
      (60 + rrand(-6,6.5) + [0,5,6,9])
    ),
    'dur': 1
  )
})

f = Fomus();
f.add(a);
f.ly;
);
```

![Example1](HelpSource/Classes/example1.svg)


```supercollider
(
p = Pbind(
        \midinote, Prand((60,60.5..80), inf),
  \dur, Prand([0.125, 0.25, 0.5], inf)
);

//p.play;

f = Fomus(p.asStream, 30);
f.ly;
f.midi;
f.xml;
)
```
![Example2](HelpSource/Classes/example2.svg)
