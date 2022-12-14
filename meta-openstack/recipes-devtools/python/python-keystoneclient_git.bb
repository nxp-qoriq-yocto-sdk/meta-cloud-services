DESCRIPTION = "Client library for OpenStack Identity API"
HOMEPAGE = "https://github.com/openstack/python-keystoneclient"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a4d0e932ffae1c0131528d30d419c55"

PR = "r0"
SRCNAME = "keystoneclient"

SRC_URI = "\
	git://github.com/openstack/python-keystoneclient.git;branch=master \
	file://fix_keystoneclient_memory_leak.patch \
	file://keystoneclient-fix-test-path-to-example-certificates.patch \
	file://keystone-api-check.sh \
	"

PV="0.11.1+git${SRCPV}"
SRCREV="89dc951f7d5a603f0c34b7c2cb9f7d26d73d9916"
S = "${WORKDIR}/git"

inherit setuptools monitor

FILES_${PN}-doc += "${datadir}/keystoneclient" 

DEPENDS += " \
        python-pip \
        python-pbr \
        "

RDEPENDS_${PN} += " \
	python-iso8601 \
	python-prettytable \
	python-requests \
	python-simplejson \
	python-oslo.config \
	python-pbr \
	"

PACKAGECONFIG ?= "bash-completion"
PACKAGECONFIG[bash-completion] = ",,bash-completion,bash-completion ${BPN}-bash-completion"

do_install_append() {
	install -d ${D}/${sysconfdir}/bash_completion.d
	install -m 664 ${S}/tools/keystone.bash_completion ${D}/${sysconfdir}/bash_completion.d

	cp -r ${S}/examples ${D}${PYTHON_SITEPACKAGES_DIR}/${SRCNAME}
}

PACKAGES =+ " ${SRCNAME}-tests ${BPN}-bash-completion"
FILES_${BPN}-bash-completion = "${sysconfdir}/bash_completion.d/*"

FILES_${SRCNAME}-tests = "${PYTHON_SITEPACKAGES_DIR}/${SRCNAME}/examples \
        "
RDEPENDS_${SRCNAME}-tests += " \
	python-httpretty \
	"

MONITOR_CHECKS_${PN} += "\
	keystone-api-check.sh \
"
