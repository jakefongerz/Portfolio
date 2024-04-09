from django.db import models

class Conv(models.Model):
    convFrom = models.CharField(max_length=10)
    convRatio = models.FloatField(max_length=10) # to troy_oz
    def __str__(self):
        return f'1 {self.convFrom} = {self.convRatio} troy_oz'

