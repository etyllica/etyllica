# Publish to Maven

- Configure .m2/settings file
- Change the version in build.xml
- Change the version in maven.xml
- ant deploy (if was a snapshot)
- ant stage (after remove -SNAPSHOT from the version name if was a release)
- Access [https://oss.sonatype.org/#stagingRepositories](https://oss.sonatype.org/#stagingRepositories)
- Important! *Close your staging Repository!*
- Check [Maven Central](http://search.maven.org/#search|ga|1|etyllica)

#### References
[Sonatype](http://central.sonatype.org/pages/apache-ant.html)
