DESCRIPTION = "gry* - a fast, shiny theme"
SECTION = "x11/data"
RDEPENDS_${PN} += "e-wm-theme-illume-gry elementary-theme-gry"
PV = "0.1"
PR = "r3"
inherit allarch
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY = "1"
