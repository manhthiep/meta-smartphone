DESCRIPTION = "nEo elementary theme - a very fast, high contrast elementary theme"
SECTION = "e/utils"
HOMEPAGE = "http://jmccloud.jm.funpic.de"
AUTHOR = "Jesus McCloud <bernd.pruenster@gmail.com"
DEPENDS = "edje-native"
RSUGGESTS_${PN} = "elementary-theme-neo e-wm-theme-illume-neo gtk-theme-neo gpe-theme-neo icon-theme-neo"
LICENSE = "MIT BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f523ab5986cc79b52a90d2ac3d5454a2"

SRCREV = "7498244a883ea21fe5835495dd54d565dad83d1d"
PV = "0.6+gitr${SRCPV}"
inherit allarch

SRC_URI = "git://git.shr-project.org/repo/shr-themes.git;protocol=http;branch=master \
          "

S = "${WORKDIR}/git/elementary/${PN}"

do_compile() {
    ${STAGING_BINDIR_NATIVE}/edje_cc -id ${S}/. -fd ${S}/. ${S}/neo.edc -o ${S}/neo.edj
}

do_install() {
    install -d ${D}${datadir}/elementary/themes/
    install -m 0644 ${S}/neo.edj ${D}${datadir}/elementary/themes/
}

FILES_${PN} = "${datadir}/elementary/themes/"
