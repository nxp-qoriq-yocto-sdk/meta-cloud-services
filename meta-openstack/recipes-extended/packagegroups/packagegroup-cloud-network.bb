SUMMARY = "Configuration for OpenStack Network node"
PR = "r0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

RDEPENDS_${PN} = " \
    neutron-plugin-openvswitch \
    neutron-dhcp-agent \
    neutron-l3-agent \
    neutron-metadata-agent \
    openvswitch-switch \
    dhcp-server \
    dhcp-client \
    dhcp-relay \
    ntp  \
    ntpdate \
    "
