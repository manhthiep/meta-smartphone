DESCRIPTION = "An e17 module for a lot of needed shr-gadgets"
HOMEPAGE = "http://shr-project.org"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "e-wm elementary"
RDEPENDS_${PN} = "e-wm"
SECTION = "x11/application"

SRCREV = "27b6c17d73b8d07e24917ed22810aed6bb455684"
PV = "0.0.0+gitr${SRCPV}"
PR = "r6"

inherit autotools

SRC_URI = "\
    git://git.shr-project.org/repo/shr-e-gadgets.git;protocol=http;branch=master \
"
S = "${WORKDIR}/git"

EXTRA_OECONF = "--with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc"

FILES_${PN} += "\
    ${datadir}/shr_elm_softkey \
    ${libdir}/enlightenment/modules/*/*.desktop \
    ${libdir}/enlightenment/modules/*/*.edj \
    ${libdir}/enlightenment/modules/*/*/*.so \
"
FILES_${PN}-staticdev += "${libdir}/enlightenment/modules/*/*/*.a"
FILES_${PN}-dev += "${libdir}/enlightenment/modules/*/*/*.la"
FILES_${PN}-dbg += "${libdir}/enlightenment/modules/*/*/.debug"

