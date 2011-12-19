DESCRIPTION = "The reference implementation of the freesmartphone.org framework APIs"
HOMEPAGE = "http://www.freesmartphone.org"
AUTHOR = "FreeSmartphone.Org Development Team"
SECTION = "console/network"
DEPENDS = "python-cython-native python-pyrex-native"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SRCREV = "5a8da900388359a7fe6370968bc16a8faee309c6"
PV = "0.9.5.9+gitr${SRCPV}"
PR = "r17"
PE = "1"

inherit distutils update-rc.d python-dir

INITSCRIPT_NAME = "frameworkd"
INITSCRIPT_PARAMS = "defaults 29"

SRC_URI = "${FREESMARTPHONE_GIT}/framework.git;protocol=git;branch=master \
           file://oeventsd-use-opimd-signals.patch \
           file://0001-oeventsd-workaround-buggy-kernel-to-get-full-vibrati.patch \
"

S = "${WORKDIR}/git"

do_configure_append() {
        echo "version=\"${PV}\"" >framework/__version__.py
}

do_install_append() {
  install -d ${D}${sysconfdir}/freesmartphone/opim/
  frameworkd_install_machine_specific_configs
}

# machines with enabled ogsmd
do_install_append_a780() {
  frameworkd_install_ogsmd_configs
}
do_install_append_eten-m800() {
  frameworkd_install_ogsmd_configs
}

frameworkd_install_ogsmd_configs() {
        # Install machine specific files
        install -d ${D}${sysconfdir}/freesmartphone/ogsmd
        install -m 0644 ${S}/etc/freesmartphone/ogsmd/cell.db ${D}${sysconfdir}/freesmartphone/ogsmd
        install -m 0644 ${S}/etc/freesmartphone/ogsmd/la.db ${D}${sysconfdir}/freesmartphone/ogsmd
        install -m 0644 ${S}/etc/freesmartphone/ogsmd/networks.tab ${D}${sysconfdir}/freesmartphone/ogsmd
}
frameworkd_install_machine_specific_configs() {
        # Fix permissions
        chmod 755 ${D}${sysconfdir}/init.d/frameworkd
        # Check for machine specific conf.
        CONF_PATH="${S}/etc"
        CONF_PATH_MACHINE="${CONF_PATH}"
        if [ -d "${CONF_PATH}/${MACHINE}" ] ; then
                CONF_PATH_MACHINE="${CONF_PATH}/${MACHINE}"
        elif [ -d "${CONF_PATH}/${MACHINE_CLASS}" ] ; then
                CONF_PATH_MACHINE="${CONF_PATH}/${MACHINE_CLASS}"
        fi
        # Install machine specific files
        install -m 0644 ${CONF_PATH_MACHINE}/frameworkd.conf ${D}${sysconfdir}

        # Check for machine specific conf.
        CONF_PATH="${S}/etc/freesmartphone/oevents"
        CONF_PATH_MACHINE="${CONF_PATH}"
        if [ -d "${CONF_PATH}/${MACHINE}" ] ; then
                CONF_PATH_MACHINE="${CONF_PATH}/${MACHINE}"
        elif [ -d "${CONF_PATH}/${MACHINE_CLASS}" ] ; then
                CONF_PATH_MACHINE="${CONF_PATH}/${MACHINE_CLASS}"
        fi
        install -m 0644 ${CONF_PATH_MACHINE}/rules.yaml ${D}${sysconfdir}/freesmartphone/oevents/
}

RDEPENDS_${PN} += "\
  fsousaged \
"

RDEPENDS_${PN} += "\
  python-ctypes \
  python-dbus \
  python-datetime \
  python-difflib \
  python-logging \
  python-pprint \
  python-pyalsaaudio \
  python-pygobject \
  python-pyrtc \
  python-pyserial \
  python-pyyaml \
  python-shell \
  python-subprocess \
  python-sqlite3 \
  python-syslog \
  python-textutils \
  python-multiprocessing \
  ${PN}-config \
"

RRECOMMENDS_${PN} += "\
  alsa-utils-amixer \
  python-gst \
  python-phoneutils \
  python-vobject \
  ppp \
"

PACKAGES =+ "${PN}-config"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# - add wmiconfig for wireless configuration
RDEPENDS_${PN}-config = "fso-sounds"
RDEPENDS_${PN}-config_append_om-gta02 = " wmiconfig"
RREPLACES_${PN}-config = "frameworkd-config-shr"
RCONFLICTS_${PN}-config = "frameworkd-config-shr"

FILES_${PN}-config = "\
  ${sysconfdir}/frameworkd.conf \
  ${sysconfdir}/freesmartphone \
  "
CONFFILES_${PN}-config = "\
  ${sysconfdir}/frameworkd.conf \
  ${sysconfdir}/freesmartphone/opreferences/conf/phone/silent.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/phone/default.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/phone/vibrate.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/phone/ring.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/profiles/default.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/rules/silent.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/rules/default.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/rules/vibrate.yaml \
  ${sysconfdir}/freesmartphone/opreferences/conf/rules/ring.yaml \
  ${sysconfdir}/freesmartphone/oevents/rules.yaml \
  "

FILES_${PN} += "${sysconfdir}/dbus-1 ${sysconfdir}/freesmartphone ${sysconfdir}/init.d ${datadir}"
FILES_${PN}-dbg += "${PYTHON_SITEPACKAGES_DIR}/framework/subsystems/*/.debug"

#EXPORT_FUNCTIONS install_machine_specific_configs install_ogsmd_configs
