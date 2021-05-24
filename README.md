# `molarmass`

I got bored in chem.

I honestly could have made something like my 
[`saved-posts`](https://github.com/caojohnny/saved-posts)
project where everything was practically in a single class,
but I decided not to.

In fact, I also decided to purposely over-engineer and
incorporate [BDD](https://docs.cucumber.io/bdd/overview/)
into the development process, just cause why not?

Essentially, what this project is intended to do is to find
the molar mass of a given formula of a substance.

## Design

The string is parsed using a state-machine-like system, based on the
last token parsed. The tokens can be a begin element state, which will
accept the second and third lowercase letters as needed, which can be
followed by an end group or begin group state, depending on the token.

The final result of the parser is a tree of values that is possible to
reverse back into the original formula entered into the parser. The tree
begins with a root node, which children consisting of the elements and
any outermost groups and their associated subscripts.

Groups are parsed by using a placeholder to hold all the child elements
until the subscript is parsed, in which case the token is then replaced
with a subscript node.

The molar mass can then be found by recursing over the children of the
root node, and their quantities can be found by using the `getCount(int)`
method to parse the previous branch's count into the current node's count.

## Building

``` shell
git clone https://github.com/caojohnny/molarmass.git
cd molarmass
mvn test install
```

Executable jar will be located in `./target`

You may also download the jar from [here](https://github.com/caojohnny/molarmass/releases/tag/1.0-SNAPSHOT).

## Use

Simple CLI interface. Begin the program, type in chemical formula. The
correct capitalization is required, subscripts are normal numbers. For
example, Barium hydroxide is `Ba(OH)2`.

If the correct formatting is used, the parser will accept the substance
as valid, even if the elements don't work together. For example, `HC2` will
be valid, even though it is not possible (as far as I am aware) for it to
form naturally.

## Demo

```
Loading config...
Loading periodic table data...
> Ba(OH)2
Molar Mass: 171.3417 g/mol

> H2O
Molar Mass: 18.015 g/mol

> H2
Molar Mass: 2.016 g/mol

> H
Molar Mass: 1.008 g/mol

> 
```

## Credits

Built with [IntelliJ IDEA](https://www.jetbrains.com/idea/)

[Cucumber](https://cucumber.io/) for BDD

[Bowserinator's Periodic Table](https://github.com/Bowserinator/Periodic-Table-JSON/) for the data
