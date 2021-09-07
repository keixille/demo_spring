#!/bin/bash

mkdir /data
mkfs -t xfs /dev/nvme0n1
mount /dev/nvme0n1 /data