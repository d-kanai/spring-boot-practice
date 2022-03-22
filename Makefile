build_jar:
	./gradlew build
put_jar::
	scp build/libs/rest-service-0.0.1-SNAPSHOT.jar dod:~
improve:
	find src -type f | xargs egrep "@IMPROVE"
