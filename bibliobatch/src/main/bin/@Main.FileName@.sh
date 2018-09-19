#!/usr/bin/env bash

SCRIPT_PATH=$( cd $(dirname $0) ; pwd -P )

# The cypath transformation is needed if we are on Windows
#if [ -e "/usr/bin/cygpath" ]
#then
 # LIB_PATH="$(cygpath -w $SCRIPT_PATH/lib/)*"
#else
 # LIB_PATH="$SCRIPT_PATH/lib/*"
  LIB_PATH="/Users/julien/IdeaProjects/bibliobatch/target/bibliobatch-0.0.1-SNAPSHOT/lib/*"
#fi
echo "$SCRIPT_PATH"
echo "$LIB_PATH"
export CLASSPATH="/Users/julien/IdeaProjects/bibliobatch/target/bibliobatch-0.0.1-SNAPSHOT/lib/*"
echo $CLASSPATH
# ${1+"$@"} is mandatory if we want to preserve the quotation of the arguments
# otherwise every space will split an argument in two
# See https://stackoverflow.com/questions/743454/space-in-java-command-line-arguments
java -jar lib/@jarFileName@ -cp "$CLASSPATH" "$@"