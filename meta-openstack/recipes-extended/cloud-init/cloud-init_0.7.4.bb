DESCRIPTION = "Init scripts for use on cloud images"
HOMEPAGE = "https://launchpad.net/cloud-init"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

PR = "r0"

SRC_URI = "https://launchpad.net/cloud-init/trunk/${PV}/+download/${BPN}-${PV}.tar.gz \
           file://cloud-init-source-local-lsb-functions.patch \
           file://distros-add-windriver-skeleton-distro-file.patch \
           file://cloud.cfg"

SRC_URI[md5sum] = "65053a7b2917ad1c26dc0a4d144b59c0"
SRC_URI[sha256sum] = "34152e0168c2ffc06284b4fe01ff43f856219228ddb995d5afe4f9a77420d779"

S = "${WORKDIR}/${BPN}-${PV}"

DISTUTILS_INSTALL_ARGS_append = " ${@base_contains('DISTRO_FEATURES', 'sysvinit', '--init-system=sysvinit_deb', '', d)}"
DISTUTILS_INSTALL_ARGS_append = " ${@base_contains('DISTRO_FEATURES', 'systemd', '--init-system=systemd', '', d)}"

MANAGE_HOSTS ?= "False"
HOSTNAME ?= ""

do_install_prepend() {
    sed -e 's:/usr/lib/${BPN}:${libdir}/${BPN}:' -i ${S}/setup.py
}

do_install_append() {
    install -m 0755 ${WORKDIR}/cloud.cfg ${D}${sysconfdir}/cloud/cloud.cfg
    if [ -z "${OPENSTACKCHEF_ENABLED}" ]; then
        sed -e "s:%MANAGE_HOSTS%:${MANAGE_HOSTS}:g" -i ${D}${sysconfdir}/cloud/cloud.cfg
        sed -e "s:%HOSTNAME%:${HOSTNAME}:g" -i ${D}${sysconfdir}/cloud/cloud.cfg
    fi
    ln -s ${libdir}/${BPN}/uncloud-init ${D}${sysconfdir}/cloud/uncloud-init
    ln -s ${libdir}/${BPN}/write-ssh-key-fingerprints ${D}${sysconfdir}/cloud/write-ssh-key-fingerprints
}

inherit setuptools update-rc.d openstackchef

CHEF_SERVICES_CONF_FILES := " \
    ${sysconfdir}/cloud/cloud.cfg \
    "

FILES_${PN} += "${sysconfdir}/* \
                ${datadir}/*"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${BPN} = "cloud-init"

RDEPENDS_${PN} = "sysklogd \
                 "
