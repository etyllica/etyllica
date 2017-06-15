# Publish to Maven

### New configuration
- Configure .m2/settings.xml file 
- Point to the new settings

```
cd ~/repo/maven
mvn install --settings ~/.m2/settings.xml
```
- Generate new keys
gpg --gen-key
- Get the Key Id
gpg2 --list-keys
- Publish your key
gpg2 --keyserver hkp://pool.sks-keyservers.net --send-keys YOURKEYID

### Update version
- Change the version in build.xml
- Change the version in maven/pom.xml
- ant deploy (if it was a snapshot)
- ant stage (after remove -SNAPSHOT from the version name if it was a release)
- Access [https://oss.sonatype.org/#stagingRepositories](https://oss.sonatype.org/#stagingRepositories)
- Close your staging repository, to automatic check if everything is ok.
- After your repository is checked as ok, click on *Release* button.
- Check [Maven Central](http://search.maven.org/#search|ga|1|etyllica)

#### References
[Sonatype - Apache Ant](http://central.sonatype.org/pages/apache-ant.html)
[Sonatype - Releasing the Deployment](http://central.sonatype.org/pages/releasing-the-deployment.html)
[Sonatype - Working with PGP Signatures](http://central.sonatype.org/pages/working-with-pgp-signatures.html)
