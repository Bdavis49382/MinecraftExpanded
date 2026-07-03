#!/usr/bin/env sh
##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS="-Xmx1g"

APP_NAME="Gradle"
APP_BASE_NAME=$(basename "$0")

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn() {
  echo "$*" >&2
}

die() {
  echo "$*" >&2
  exit 1
}

# OS specific support (must be 'true' or 'false').
cygwin=false
ios=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  Darwin*) ios=true ;;
esac

# Resolve symbolic links
PRG="$0"
while [ -h "$PRG" ] ; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")"/$link
  fi
 done

SAVED="$(pwd)"
cd "$(dirname "$PRG")" >/dev/null
PRG_DIR="$(pwd -P)"
cd "$SAVED" >/dev/null

# Set home for the application
APP_HOME="${PRG_DIR}"/.

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
  if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
    JAVACMD="$JAVA_HOME/jre/sh/java"
  else
    JAVACMD="$JAVA_HOME/bin/java"
  fi
  if [ ! -x "$JAVACMD" ] ; then
    die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
  fi
else
  JAVACMD="java"
  if ! command -v "$JAVACMD" >/dev/null 2>&1 ; then
    die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
  fi
fi

# Increase the maximum file descriptor limit, if possible.
if [ "$MAX_FD" != "maximum" ] ; then
  MAX_FD_LIMIT="$MAX_FD"
fi

if [ -n "$MAX_FD_LIMIT" ] ; then
  try_java="${JAVACMD}"
  if [ "$cygwin" = true ] ; then
    try_java="$(cygpath --unix "$JAVACMD")"
  fi
  if [ "$try_java" = "" ] ; then
    die "ERROR: JAVA command could not be resolved."
  fi
fi

CLASSPATH="${APP_HOME}gradle/wrapper/gradle-wrapper.jar"

# Collect all arguments for the java command.
save () {
  SAVE="$@"
}
save "$@"

# Setup the command line.
QUOTED_ARGS=""
for arg in "$@"; do
  QUOTED_ARGS="$QUOTED_ARGS \"$arg\""
done

exec "$JAVACMD" $DEFAULT_JVM_OPTS -cp "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
