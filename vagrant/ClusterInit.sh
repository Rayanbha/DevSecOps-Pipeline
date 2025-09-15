#!/bin/bash
set -e


sudo kubeadm init \
  --apiserver-advertise-address="192.168.56.12" \
  --upload-certs \
  --pod-network-cidr=192.168.0.0/16 \
  --cri-socket=unix:///var/run/crio/crio.sock 


# Copy the kubeconfig file to the home directory
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl apply -f https://raw.githubusercontent.com/projectcalico/calico/v3.30.0/manifests/calico.yaml

# Install Calico CNI plugin
kubectl create namespace frontend || echo "Namespace frontend already exists"
kubectl create namespace backend || echo "Namespace backend already exists"

echo "Namespaces created."