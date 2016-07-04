# Publish to Maven

- Configure .m2/settings file
- Change the version in build.xml
- Change the version in maven.xml
- ant deploy (if was a snapshot)
- ant stage (after remove -SNAPSHOT from the version name if was a release)
- Access [https://oss.sonatype.org/#stagingRepositories](https://oss.sonatype.org/#stagingRepositories)
- Close your staging repository, to automatic check if everything is ok.
- After your repository is checked as ok, click on *Release* button.
- Check [Maven Central](http://search.maven.org/#search|ga|1|etyllica)

#### References
[Sonatype - Apache Ant](http://central.sonatype.org/pages/apache-ant.html)
[Sonatype - Releasing the Deployment](http://central.sonatype.org/pages/releasing-the-deployment.html)
