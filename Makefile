build_jar:
	./gradlew build
put_jar::
	scp build/libs/rest-service-0.0.1-SNAPSHOT.jar dod:~
improve:
	find src -type f | xargs egrep "@IMPROVE"
sonar:
	./gradlew sonarqube -Dsonar.projectKey=sampleSonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7d344ab87866d7bf8103b7c543edb6f8f0144558
