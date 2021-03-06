DESCRIPTION = "The freesmartphone.org Framework 2.0. \
Install this task to make your distribution FSO 2.0-compliant."
SECTION = "fso/base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PV = "1.9.0"
PR = "r20"

inherit packagegroup allarch

RPROVIDES_${PN} += "task-fso2-compliance"
RREPLACES_${PN} += "task-fso2-compliance"
RCONFLICTS_${PN} += "task-fso2-compliance"

RDEPENDS_${PN} = "\
  fsoaudiod-config \
  fsodatad \
  fsodeviced \
  fsogsmd \
  fsotdld \
  fsonetworkd \
  fsousaged \
  \
  fso-apm \
"

RSUGGESTS_${PN} = "\
  fsoaudiod \
  \
  connman \
  connman-plugin-bluetooth \
  connman-plugin-ethernet \
  connman-plugin-fake \
  connman-plugin-loopback \
  connman-plugin-wifi \
"

RRECOMMENDS_${PN} = "\
  wmiconfig \
  tzdata \
  tzdata-africa \
  tzdata-americas \
  tzdata-asia \
  tzdata-australia \
  tzdata-europe \
"
