#!/usr/bin/env bash

CMD="$1"
shift

dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)


export CP="$(clojure -Spath):$dir/src"

init() {
 brew install clojure/tools/clojure
 brew install borkdude/brew/babashka
}

case "$CMD" in 
  bb )
  echo bb -cp "$CP" $@     
  ;;
  repeat )
   bb -cp $CP -m commands.repeat $@
   ;;
  docker )
   bb -cp $CP -m docker.core $@
   ;;
  install )
   bb -cp "$CP" -m install.mactools $@
   bb -cp $CP -m install.clojure $@
   ;;
  init )
   init
   ;;
  repl )
   clj \
-Sdeps '{:deps {vlaaad/reveal {:mvn/version "1.0.128"}}}' \
-m vlaaad.reveal repl
   ;;
  * )
  echo "$0 init"
  exit 1
esac
