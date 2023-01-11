FROM openjdk:11
WORKDIR /app
RUN wget https://repository-master.mulesoft.org/nexus/content/repositories/releases/org/mule/distributions/mule-standalone/4.4.0/mule-standalone-4.4.0.tar.gz
RUN tar xzf /app/mule-standalone-4.4.0.tar.gz
RUN wget https://download.tanukisoftware.com/wrapper/3.5.51/wrapper-linux-arm-64-3.5.51.tar.gz
RUN tar xzf wrapper-linux-arm-64-3.5.51.tar.gz
RUN cp wrapper-linux-arm-64-3.5.51/lib/libwrapper.so mule-standalone-4.4.0/lib/boot/libwrapper-linux-aarch64-64.so
RUN cp wrapper-linux-arm-64-3.5.51/lib/wrapper.jar mule-standalone-4.4.0/lib/boot/wrapper-v3.5.51.jar
RUN cp wrapper-linux-arm-64-3.5.51/bin/wrapper mule-standalone-4.4.0/lib/boot/exec/wrapper-linux-aarch-64
COPY mule mule-standalone-4.4.0/bin/mule
RUN chmod +x mule-standalone-4.4.0/bin/mule
COPY target/mulesoa-1.0.0-SNAPSHOT-mule-application.jar mule-standalone-4.4.0/apps/mulesoa.jar
CMD ["sh", "mule-standalone-4.4.0/bin/mule"]