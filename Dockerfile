FROM ubuntu:latest
LABEL authors="baghd"

ENTRYPOINT ["top", "-b"]