from django.http import JsonResponse
from django.shortcuts import render
from .models import Conv

def convert(request):
    if 'from' not in request.GET or 'value' not in request.GET or 'to' not in request.GET:
        resp = { "error": "Invalid unit conversion request (missing parem)" }
    else:
        to = request.GET['to']
        val = request.GET['value']
        frOm =  request.GET['from']
        val = doConv(to, frOm, val)
        units = to
        resp = { 'units': units, 'value': val}

    j = JsonResponse(resp)
    if 'Origin' in request.headers:
        j['Access-Control-Allow-Origin'] = request.headers['Origin']
        return j
    else:
        j['Access-Control-Allow-Origin'] = "*"
        return j

def doConv(to, cFrom, amount):
    all = Conv.objects.all()
    timesRatio = 1
    divRatio = 1

    for obj in all:
        if obj.convFrom == cFrom:
            timesRatio = obj.convRatio
        if obj.convFrom == to:
            divRatio = obj.convRatio

    return float(amount) * float(timesRatio) / float(divRatio)