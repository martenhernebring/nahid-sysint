#!/bin/bash
#lwp-request -m GET -e 'http://localhost:8080/v1?format=xml' | grep "Content-Type";
lwp-request -m GET -e 'http://localhost:8080/v1?format=xml&day=2018-01-16';
