FROM quay.io/wildfly/wildfly:latest-jdk11
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
RUN /opt/jboss/wildfly/bin/add-user.sh user qwerty123 --silent -a
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]