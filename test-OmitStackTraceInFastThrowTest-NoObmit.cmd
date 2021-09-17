call mvn -DskipTests clean install
java -XX:-OmitStackTraceInFastThrow -cp target\playground-1.0.0-jar-with-dependencies.jar mc.playground.weird.OmitStackTraceInFastThrowTest