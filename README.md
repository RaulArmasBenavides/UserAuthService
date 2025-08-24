VSCODE

Use the following commands :

./gradlew clean build --refresh-dependencies
./gradlew bootRun

./gradlew dependencies
./gradlew dependencyInsight --dependency nombre

./gradlew test
./gradlew dependencyInsight --dependency nombre
./gradlew javadoc

docker build -t userauthservice .
docker run -d -p 8002:8002 --name userauthservice userauthservice