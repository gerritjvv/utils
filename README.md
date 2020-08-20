# utils
personal automated tasks 

# Docs

https://practicalli.github.io/clojure/clojure-tools/install/
https://github.com/borkdude/babashka

## Getting started

```

./build.sh init

```

## Running 

Repl:

```
eval `./build.sh bb --repl`
```


# Commands

## Docker

```
./build.sh docker prune
```


## Repeatig shell commands

```
# run ls /tmp/ then wait 5 seconds and run again
./build.sh repeat 5 ls /tmp/
```
