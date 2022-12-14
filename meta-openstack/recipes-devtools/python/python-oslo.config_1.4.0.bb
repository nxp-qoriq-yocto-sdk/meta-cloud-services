DESCRIPTION = "API supporting parsing command line arguments and .ini style configuration files."
HOMEPAGE = "https://pypi.python.org/pypi/oslo.config/1.1.0"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c46f31914956e4579f9b488e71415ac8"

PR = "r0"
SRCNAME = "oslo.config"

SRC_URI = "https://pypi.python.org/packages/source/o/${SRCNAME}/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "55bc1515401b92252685c94d556a7257"
SRC_URI[sha256sum] = "739e76e0a83ec585a3c0415140d9e6ba1b14c7f207e33643e8af7eb4346ffe07"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

DEPENDS += " \
        python-pbr \
        python-pip \
        "

RDEPENDS_${PN} += "python-pbr"