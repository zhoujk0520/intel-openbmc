SUMMARY = "libmctp_intel"
DESCRIPTION = "Implementation of MCTP(DMTF DSP0236)"

SRC_URI = "git://github.com/Intel-BMC/libmctp.git;protocol=ssh"
SRCREV = "a077c8ca846574509983d10aaa33de943ab6388a"

S = "${WORKDIR}/git/"

PV = "1.0+git${SRCPV}"

LICENSE = "Apache-2.0 | GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0d30807bb7a4f16d36e96b78f9ed8fae"

inherit cmake

DEPENDS += "i2c-tools"
