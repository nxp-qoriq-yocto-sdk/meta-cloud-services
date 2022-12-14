DESCRIPTION = "CLI and python client library for OpenStack Neutron"
HOMEPAGE = "https://launchpad.net/neutron"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

DEPENDS += " \
        python-pip \
        python-pbr \
        "

RDEPENDS_${PN} +="python-cliff \
	python-httplib2 \
	python-iso8601 \
	python-prettytable \
	python-pyparsing \
	python-simplejson \
	python-pbr \
	"

PR = "r0"

SRC_URI = "git://github.com/openstack/python-neutronclient.git;branch=master \
           file://neutronclient-use-csv-flag-instead-of-json.patch \
           file://neutron-api-check.sh \
          "

PV="2.3.9+git${SRCPV}"
SRCREV="170f8ca438bf17b5ec679cb3e5135c97dfdeb6c2"
S = "${WORKDIR}/git"

inherit setuptools monitor

PACKAGECONFIG ?= "bash-completion"
PACKAGECONFIG[bash-completion] = ",,bash-completion,bash-completion ${BPN}-bash-completion"

do_install_append() {
	install -d ${D}/${sysconfdir}/bash_completion.d
	install -m 664 ${S}/tools/neutron.bash_completion ${D}/${sysconfdir}/bash_completion.d
}

PACKAGES =+ "${BPN}-bash-completion"
FILES_${BPN}-bash-completion = "${sysconfdir}/bash_completion.d/*"

MONITOR_CHECKS_${PN} += "\
	neutron-api-check.sh \
"
