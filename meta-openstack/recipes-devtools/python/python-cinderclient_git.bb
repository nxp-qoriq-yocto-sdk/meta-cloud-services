DESCRIPTION = "Client library for OpenStack Cinder API."
HOMEPAGE = "https://github.com/openstack/python-cinderclient"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3572962e13e5e739b30b0864365e0795"
DEPENDS = "python-setuptools-git"

PR = "r0"
SRCNAME = "python-cinderclient"

SRC_URI = "\
	git://github.com/openstack/python-cinderclient.git;branch=master \
	file://fix_cinderclient_memory_leak.patch \
	file://cinder-api-check.sh \
	"

PV="1.1.1+git${SRCPV}"
SRCREV="f5d090844aff09d9db597f3cc88ea2e439d9a6b7"
S = "${WORKDIR}/git"

inherit setuptools monitor

DEPENDS += " \
        python-pip \
        python-pbr \
        "

RDEPENDS_${PN} += "python-prettytable \
                   python-simplejson \
                   python-requests \
                   python-setuptools-git \
                   python-pbr \
	           "

PACKAGECONFIG ?= "bash-completion"
PACKAGECONFIG[bash-completion] = ",,bash-completion,bash-completion ${BPN}-bash-completion"

do_install_append() {
	install -d ${D}/${sysconfdir}/bash_completion.d
	install -m 664 ${S}/tools/cinder.bash_completion ${D}/${sysconfdir}/bash_completion.d
}

PACKAGES =+ "${BPN}-bash-completion"
FILES_${BPN}-bash-completion = "${sysconfdir}/bash_completion.d/*"

MONITOR_CHECKS_${PN} += "\
	cinder-api-check.sh \
"
