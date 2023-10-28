FROM tomcat:8.5-jre8

# Copy the war file to the container
COPY ./*.war /usr/local/tomcat/webapps/r7.war

# Set the default port
EXPOSE 8080

# Set the default command
CMD ["catalina.sh", "run"]