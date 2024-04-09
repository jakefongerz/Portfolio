from django.http import HttpResponse
from django.shortcuts import render
from django.http import JsonResponse

def plan(request):
    context = {}
    return render(request, 'gold/plan.html', context)


def index(request):
        context = {'content': 'initial content'}
        return render(request, 'gold/index.html', context)