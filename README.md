# Inicializar imagen en docker
docker build  -t "test-gruposalinas-springboot" .

# correr imagen en docker
docker run -d -p 8080:8080 test-gruposalinas-springboot

# Ver imagenes corriendo
docker ps -a

# Para aplicación en docker (colocar el id de la imagen)
docker stop id

# Swagger-UI
http://localhost:8080/test-gs/swagger-ui/index.html