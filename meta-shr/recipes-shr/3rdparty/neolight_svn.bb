DESCRIPTION = "An application to turn your mobile device into a flashlight"
HOMEPAGE = "http://code.google.com/p/neolight/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"
AUTHOR = "Valéry Febvre <vfebvre@easter-eggs.com>"
SECTION = "x11/applications"

DEPENDS = "python-native edje-native"

SRCREV = "16"
PV = "1.4.0+svnr${SRCPV}"
PR = "r3"

SRC_URI = "svn://neolight.googlecode.com/svn;module=trunk;protocol=http"
S = "${WORKDIR}/trunk"

inherit distutils

FILES_${PN} += "${datadir}/neolight ${datadir}/applications/neolight.desktop ${datadir}/pixmaps/neolight.png"

RDEPENDS_${PN} += "python-edbus python-elementary"

do_compile_prepend() {
    ${STAGING_BINDIR_NATIVE}/edje_cc -id ${S}/data ${S}/data/neolight.edc
}
