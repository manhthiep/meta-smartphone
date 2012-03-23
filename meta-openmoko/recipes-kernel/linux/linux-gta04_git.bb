require recipes-kernel/linux/linux.inc

KERNEL_RELEASE = "3.2.0"
PV = "${KERNEL_RELEASE}+gitr${SRCPV}"
PR = "r2"
PE = "3"

SRCREV = "5d298fede5f6c7de5392b784f44ac63a44f04c62"

SRC_URI = "\
  git://git.freesmartphone.org/linux-2.6.git;protocol=git;branch=3.2-gta04 \
  file://defconfig \
"
S = "${WORKDIR}/git"

CMDLINE_om-gta04 = "console=ttyO2,115200n8 mpurate=800 vram=12M omapfb.rotate_type=0 omapdss.def_disp=lcd root=/dev/mmcblk0p2 rw rootfstype=ext3 rootwait twl4030_charger.allow_usb=1 twl4030_charger.charge_backup=1 musb_hdrc.preserve_vbus=1 log_buf_len=8M ignore_loglevel no_console_suspend"

# Mark archs/machines that this kernel supports
COMPATIBLE_MACHINE = "om-gta04"

DEFAULT_PREFERENCE = "-2"
DEFAULT_PREFERENCE_om-gta04 = "1"
