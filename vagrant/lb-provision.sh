#!/bin/bash

set -e

apt-get update
apt-get install -y haproxy

cat <<EOF > /etc/haproxy/haproxy.cfg
global
    log /dev/log local0
    log /dev/log local1 notice
    daemon
    maxconn 2048

defaults
    log     global
    mode    tcp
    option  tcplog
    timeout connect 10s
    timeout client  1m
    timeout server  1m

frontend kubernetes-api
    bind *:6443
    default_backend k8s-masters

backend k8s-masters
    balance roundrobin
    option tcp-check
    server master1 192.168.1.11:6443 check
    server master2 192.168.1.12:6443 check

EOF

systemctl restart haproxy

systemctl enable haproxy
