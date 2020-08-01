#!/usr/bin/env bash

CMD="$1"
shift

dir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)

export CP="$dir/src"

init() {

 # install babaska
 curl -s https://raw.githubusercontent.com/borkdude/babashka/master/install -o install-babashka
 chmod +x install-babashka && sudo ./install-babashka

}


case "$CMD" in 
  bb )
  echo bb -cp "$CP" $@     
  ;;
  docker )
   bb -cp $CP -m docker.core $@
   ;;
  init )
   init
   ;;
  * )
  echo "$0 init"
  exit 1
esac
