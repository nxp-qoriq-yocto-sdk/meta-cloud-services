DESCRIPTION = "CLI and python client library for OpenStack Sahara"
HOMEPAGE = "https://launchpad.net/sahara"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1dece7821bf3fd70fe1309eaa37d52a2"

DEPENDS += " \
        python-pip \
        python-pbr \
        "

RDEPENDS_${PN} += " \
	python-pbr \
	"

SRCNAME = "saharaclient"

SRC_URI = "git://github.com/openstack/python-saharaclient.git;branch=master"

PV="0.7.4+git${SRCPV}"
SRCREV="e9f953fc846dbbd05f81a227f3d87c0028fb2d85"
S = "${WORKDIR}/git"

inherit setuptools

