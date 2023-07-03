FROM bash:5-alpine3.15

RUN echo "echo 'I successfully used docker' | sha256sum" > checksum.sh
RUN chmod +x checksum.sh
CMD [ "bash", "/checksum.sh" ]
