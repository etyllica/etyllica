#!bin/sh
# Script to deploy etyllica
# Usage sh deploy.sh version
# Example: sh deploy.sh 1.0.0

VERSION=$1

if [ -z "$1" ]
then
  echo 'Command expects a version as in: sh deploy 1.0.0'
else
  # Edit build.xml
  sed -i -e 's/name="version" value="[^"]*"/name="version" value="'"$VERSION"'"/g' build.xml
  # Edit maven/pom.xml
  sed -i -E "s|(<version>)(.*)(</version>)|\1$VERSION\3|" maven/pom.xml
  # Run ant stage
  ant stage
  echo 'Access: https://oss.sonatype.org/#stagingRepositories'
fi



