mvnw.cmd clean install -DskipTests=true -s ../settings.xml && ^
docker build . -t hrd-registry.hrd.cloudzcp.net/clouddance/user-service:0.1.0 && ^
docker push hrd-registry.hrd.cloudzcp.net/clouddance/user-service:0.1.0